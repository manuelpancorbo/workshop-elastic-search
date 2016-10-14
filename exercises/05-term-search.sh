#!/usr/bin/env bash

# Looks for exact term
# And yes, a GET request can have body!
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "term": {
      "zipCode": "08001"
    }
  }
}
' | python -m json.tool

# Short form for simple queries
curl -XGET "http://localhost:9200/ads/ad/_search?q=zipCode:08001"

# Term search for analyzed field
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "term": {
      "title": "thing"
    }
  }
}
' | python -m json.tool
