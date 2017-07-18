#!/usr/bin/env bash

# Match all, score will always be 1
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool": {
        "filter": {
            "range": {
              "price": {
                "gte": 15,
                "lte": 20.6
              }
            }
        }
    }
  }
}
' | python -m json.tool


# Query has search, score will be variable
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool": {
      "must": {
        "match": {
          "title": {
            "query": "Another beautiful thing",
            "operator": "and"
          }
        }
      },
      "filter": {
        "range": {
          "price": {
            "gte": 15,
            "lte": 20.6
          }
        }
      }
    }
  }
}
' | python -m json.tool


curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool": {
      "must": {
        "term": {
          "title": "thing"
        }
      },
      "filter": {
        "range": {
          "price": {
            "gte": 15,
            "lte": 20.6
          }
        }
      }
    }
  }
}
' | python -m json.tool