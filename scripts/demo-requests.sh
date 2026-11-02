#!/usr/bin/env bash
set -euo pipefail
base_url="${BASE_URL:-http://127.0.0.1:8080}"
curl -fsS -X POST "$base_url/api/orders" -H 'Content-Type: application/json' \
  -d '{"customerName":"Ada Lovelace","productName":"Mechanical keyboard","quantity":1,"status":"PENDING"}'
printf '\n'; curl -fsS "$base_url/api/orders/1"; printf '\n'
curl -fsS -X POST "$base_url/api/orders/1/cancel"; printf '\n'
curl -fsS "$base_url/api/admin/orders/1/summary"; printf '\n'
curl -fsS "$base_url/api/admin/orders/export"; printf '\n'
