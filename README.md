# Order Management Demo — Graphify map

This Spring Boot order-management demo includes a generated Graphify knowledge graph. Use it to explore the codebase’s architecture, dependencies, and likely change impact without having to trace every file manually.

## Relationship confidence

Every graph relationship includes a confidence tag. Use it to distinguish direct
source evidence from relationships that need more scrutiny:

| Tag | Meaning |
| --- | --- |
| `EXTRACTED` | Found directly in source, such as an import or function call. Its confidence is always `1.0`. |
| `INFERRED` | A derived relationship with a `confidence_score`: `0.95` near-certain, `0.85` strong evidence, `0.75` reasonable, `0.65` weak, and `0.55` speculative. |
| `AMBIGUOUS` | Uncertain relationship flagged in the Graph Report for manual review. |

## Explore the graph

Start with the generated [Graph Report](graphify-out/GRAPH_REPORT.md), which summarizes the graph and highlights architectural hubs. For visual exploration, open these generated files locally in a browser:

- [Interactive graph](graphify-out/graph.html)
- [Architecture and call flow](graphify-out/order-management-demo-callflow.html)
- [Collapsible graph tree](graphify-out/GRAPH_TREE.html)
- [Graph data (JSON)](graphify-out/graph.json)
- [Generated codebase wiki](graphify-out/wiki/index.md)

The current graph contains 134 nodes and 234 edges across 11 communities. Its primary hubs are `Order`, `OrderService`, and `OrderExportService`; see the report for the generated analysis, cycles, communities, and suggested investigation questions.

Refresh the artifacts after code changes:

```bash
graphify update .
```

## Recommended Graphify v8 workflow

The generated graph is committed to this repository, so every clone starts with a
shared architecture map. Set up Graphify once per clone, then let its Git hooks
handle commits and branch changes:

```bash
# Install the Graphify skill in this repository for Codex.
graphify install --project --platform codex

# Rebuild after commits and branch switches.
graphify hook install
```

Run `graphify update .` after `git pull`, merges, or whenever a query appears
stale. The hooks update the graph for commits and branch switches, but not for
incoming changes from a pull. If a refactor removes many files, use the forced
refresh so Graphify can replace a smaller graph:

```bash
graphify update . --force
```

For GitHub pull-request work, Graphify can also show the graph impact alongside
CI and review state:

```bash
graphify prs
graphify prs --conflicts
```

Then use Graphify to investigate the project, for example:

```bash
# Scope the code involved in an architecture or behavior question.
graphify query "How does an order cancellation request move from the API to persistence?"

# Inspect one node, including its source location and direct relationships.
graphify explain "OrderExportService"

# Trace the shortest dependency path between two mapped concepts.
graphify path "AdminOrderController" "OrderRepository"

# See which symbols are likely affected before changing the order entity.
graphify affected "src/main/java/com/orders/Order.java"
```

## Graphify command reference

These are reference commands only. Run them from a project directory where appropriate. Output paths below are defaults; options such as `--output`, `--dir`, and `--out` can change them.

| Command | Description |
| --- | --- |
| `uv tool install graphifyy` | Installs the official Graphify package (the package name has two `y` characters). |
| `graphify install --project --platform codex` | Installs the Codex skill files in this repository for a shared project setup. |
| `graphify install --platform codex` | Installs Graphify's Codex integration for the current user. |
| `graphify codex install` | Creates or updates the current project's `AGENTS.md` with Graphify instructions for Codex. |
| `graphify update .` | Scans the project and creates or refreshes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md`. |
| `graphify update . --force` | Refreshes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md`, even when the rebuilt graph has fewer nodes. |
| `graphify extract .` | Runs full semantic extraction, including LLM-assisted relationships when configured, and writes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md`. |
| `graphify cluster-only .` | Re-clusters an existing graph and refreshes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md` without re-extracting code. |
| `graphify export html --graph graphify-out/graph.json` | Writes the interactive visualization to `graphify-out/graph.html`. |
| `graphify query "question"` | Searches the graph to answer an architecture or codebase question. |
| `graphify explain "node"` | Explains a file, symbol, or graph node and its relationships. |
| `graphify path "A" "B"` | Finds the shortest graph path between two nodes. |
| `graphify affected "node"` | Finds code likely impacted by changes to a file or symbol. |
| `graphify god-nodes` | Lists the most connected nodes, useful for identifying architectural hubs. |
| `graphify watch .` | Watches the project and refreshes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md` after changes. |
| `graphify check-update .` | Checks whether the graph needs a refresh. |
| `graphify tree` | Writes a collapsible HTML tree to `graphify-out/GRAPH_TREE.html`. |
| `graphify export callflow-html` | Writes an HTML call-flow or architecture diagram to `graphify-out/<project>-callflow.html`. |
| `graphify export svg` | Writes an SVG export to `graphify-out/graph.svg`. |
| `graphify export graphml` | Writes a GraphML export to `graphify-out/graph.graphml`. |
| `graphify export obsidian` | Creates an Obsidian vault in `graphify-out/obsidian/`, including Markdown notes and `graph.canvas`. |
| `graphify export wiki` | Creates a Markdown wiki in `graphify-out/wiki/`, including `index.md` and generated articles. |
| `graphify export neo4j` | Writes a Cypher import script to `graphify-out/cypher.txt`; with `--push`, it loads the graph into Neo4j instead. |
| `graphify hook install` | Creates Git hooks such as `.git/hooks/post-commit` and `.git/hooks/post-checkout` to keep graphs updated. |
| `graphify hook status` | Shows whether Graphify Git hooks are installed. |
| `graphify hook uninstall` | Removes Graphify Git hooks. |
| `graphify add "URL"` | Downloads the URL into `raw/` and refreshes `graphify-out/graph.json`, `graphify-out/graph.html`, and `graphify-out/GRAPH_REPORT.md`. |
| `graphify clone GITHUB_URL` | Clones a GitHub repository under `~/.graphify/repos/<owner>/<repo>` for analysis. |
| `graphify merge-graphs graph-a.json graph-b.json` | Combines multiple project graphs into `graphify-out/merged-graph.json`. |
| `graphify global add graphify-out/graph.json` | Adds or updates this project in the global multi-repository graph at `~/.graphify/global-graph.json`. |
| `graphify global list` | Lists graphs registered in the global graph. |
| `graphify diagnose multigraph` | Detects possible lost edges caused by same-endpoint edge collapsing. |
| `graphify benchmark graphify-out/graph.json` | Measures graph-query context reduction versus reading the whole codebase. |
| `graphify prs` | Shows GitHub pull requests with their CI, review, worktree, and graph-impact context. |
| `graphify prs --conflicts` | Finds pull requests that touch overlapping graph communities and may have merge-order risk. |

## Typical Codex workflow

```bash
graphify install --project --platform codex
graphify hook install
graphify update .
graphify query "your question"
graphify affected "src/main/java/com/orders/OrderService.java"
```
