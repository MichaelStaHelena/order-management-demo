# Order Management Demo — Graphify map

This Spring Boot order-management demo includes a generated Graphify knowledge graph. Use it to explore the codebase’s architecture, dependencies, and likely change impact without having to trace every file manually.

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

Then use Graphify to investigate the project, for example:

```bash
graphify query "How does order export depend on the order service?"
graphify affected "src/main/java/com/orders/OrderService.java"
```

## Graphify command reference

These are reference commands only. Run them from a project directory where appropriate.

| Command | Description |
| --- | --- |
| `graphify install --platform codex` | Installs Graphify's Codex integration for the current user. |
| `graphify codex install` | Adds Graphify instructions to the current project's `AGENTS.md` for Codex. |
| `graphify update .` | Scans the project and generates or refreshes graph JSON and HTML outputs. |
| `graphify extract .` | Runs full semantic extraction, including LLM-assisted relationships when configured. |
| `graphify cluster-only .` | Re-clusters an existing graph and regenerates reports and visuals without re-extracting code. |
| `graphify export html --graph graphify-out/graph.json` | Produces an interactive HTML visualization from an existing graph. |
| `graphify query "question"` | Searches the graph to answer an architecture or codebase question. |
| `graphify explain "node"` | Explains a file, symbol, or graph node and its relationships. |
| `graphify path "A" "B"` | Finds the shortest graph path between two nodes. |
| `graphify affected "node"` | Finds code likely impacted by changes to a file or symbol. |
| `graphify god-nodes` | Lists the most connected nodes, useful for identifying architectural hubs. |
| `graphify watch .` | Watches the project and rebuilds the graph after changes. |
| `graphify check-update .` | Checks whether the graph needs a refresh. |
| `graphify tree` | Generates a collapsible HTML tree of the graph. |
| `graphify export callflow-html` | Creates an HTML call-flow or architecture diagram. |
| `graphify export svg` | Exports the graph as SVG. |
| `graphify export graphml` | Exports the graph in GraphML format. |
| `graphify export obsidian` | Creates Obsidian notes and a canvas from the graph. |
| `graphify export wiki` | Creates Markdown wiki articles from the graph. |
| `graphify export neo4j` | Exports Cypher or pushes the graph into Neo4j. |
| `graphify hook install` | Installs Git hooks to keep graphs updated after commits and checkouts. |
| `graphify hook status` | Shows whether Graphify Git hooks are installed. |
| `graphify hook uninstall` | Removes Graphify Git hooks. |
| `graphify add "URL"` | Downloads a URL into the corpus and updates the graph. |
| `graphify clone GITHUB_URL` | Clones a GitHub repository locally for analysis. |
| `graphify merge-graphs graph-a.json graph-b.json` | Combines multiple project graphs. |
| `graphify global add graphify-out/graph.json` | Adds this project graph to Graphify's global multi-repository graph. |
| `graphify global list` | Lists graphs registered in the global graph. |
| `graphify diagnose multigraph` | Detects possible lost edges caused by same-endpoint edge collapsing. |
| `graphify benchmark graphify-out/graph.json` | Measures graph-query context reduction versus reading the whole codebase. |

## Typical Codex workflow

```bash
graphify codex install
graphify update .
graphify query "your question"
graphify affected "changed/file.py"
```
