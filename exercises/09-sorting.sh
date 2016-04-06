#!/usr/bin/env bash

curl -XGET "http://ws-elastic.dm:9200/ads/ad/_search" -d '
{
  "query": {
    "match_all": {}
  },
  "sort": {
    "price": "desc",
    "id": "desc"
  }
}
' | python -m json.tool