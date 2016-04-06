#!/usr/bin/env bash

curl -XGET "http://ws-elastic.dm:9200/ads/ad/_search" -d '
{
  "query": {
    "match": {
      "title": "Another beautiful thing"
    }
  }
}
' | python -m json.tool


curl -XGET "http://ws-elastic.dm:9200/ads/ad/_search" -d '
{
  "query": {
    "match": {
      "title": {
        "query": "Another beautiful thing",
        "operator": "and"
       }
    }
  }
}
' | python -m json.tool

curl -XGET "http://ws-elastic.dm:9200/ads/ad/_search" -d '
{
  "query": {
    "match": {
      "title": {
        "query": "Another beautiful thing",
        "minimum_should_match": "75%"
       }
    }
  }
}
' | python -m json.tool