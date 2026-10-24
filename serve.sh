#!/usr/bin/env bash
PORT=${1:-8080}
echo "Starting local server at http://127.0.0.1:$PORT"
echo "You can view:"
echo " - Cloned app:   http://127.0.0.1:$PORT/index.html"
echo " - Standalone:   http://127.0.0.1:$PORT/../coderpetro_webpage.html"
python3 -m http.server "$PORT"
