#!/bin/bash

PROJECT_DIR="../kp-wt-1500-chmurki"

BACKEND_DIR="../kp-wt-1500-chmurki"

FRONTEND_DIR="$PROJECT_DIR/frontend"


start_backend() {
  echo "Start backend..."
  cd "$BACKEND_DIR"
  ./gradlew bootRun
}


start_frontend() {
  echo "Start frontendu..."
  cd "$FRONTEND_DIR"
  npm install 
  npm start
}


main() {

  start_backend &

  while ! curl -s http://localhost:8080 >/dev/null; do
    sleep 3
  done

  if [ $? -eq 0 ]; then
    start_frontend
  else
    echo "Error while running backend. Cannot run frontend"
  fi
}


main