#!/usr/bin/env bash
set -euo pipefail

root="$(cd "$(dirname "$0")/.." && pwd)"
port="${VERIFY_PORT:-18080}"
log_file="${TMPDIR:-/tmp}/order-management-demo-verify.log"
pid=""

cleanup() {
  if [[ -n "$pid" ]] && kill -0 "$pid" 2>/dev/null; then
    kill "$pid" || true
  fi
}
trap cleanup EXIT

cd "$root"
./mvnw -q test
./mvnw -q package -DskipTests
java -jar target/order-management-demo-0.0.1-SNAPSHOT.jar \
  --server.port="$port" --spring.profiles.active=demo >"$log_file" 2>&1 &
pid="$!"

for _ in $(seq 1 25); do
  if curl -fsS "http://127.0.0.1:$port/api/orders" >/dev/null 2>&1; then
    break
  fi
  sleep 1
done

if ! curl -fsS "http://127.0.0.1:$port/api/admin/orders/export" | grep -q 'customerName'; then
  echo "FAIL: API smoke test failed. Application log: $log_file" >&2
  tail -80 "$log_file" >&2 || true
  exit 1
fi

echo "PASS: tests and localhost API smoke test completed on port $port."
