#!/usr/bin/env bash

while IFS='' read -r line || [[ -n "$line" ]]; do

    curl -XPOST -H "Content-type: application/json" "http://localhost:8000/adverts" -d "$line"

done < "fixture-ads.txt"