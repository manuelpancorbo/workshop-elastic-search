#!/usr/bin/env bash

curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "range": {
      "price": {
        "gte": 15,
        "lte": 20.6
      }
    }
  }
}
' | python -m json.tool