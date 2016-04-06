#!/usr/bin/env bash

# Looks for exact term
# And yes, a GET request can have body!
curl -XGET "http://ws-elastic.dm:9200/ads/ad/_search" -d '
{
  "query": {
    "term": {
      "title": "thing"
    }
  }
}
' | python -m json.tool
