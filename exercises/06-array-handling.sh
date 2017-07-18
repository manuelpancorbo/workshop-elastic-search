#!/usr/bin/env bash

# Saved as received, array fits in any mapping.
curl -XPUT "http://localhost:9200/ads/ad/10" -d'
{
    "id": 10,
    "title": "Anohter one... really?",
    "body": "bahhh.",
    "price": 2000,
    "location": [2.1660139, 41.3791979],
    "zipCode": [ "08001", "08002" ]
}' | python -m json.tool

# Will retrieve all ads
curl -XGET "http://localhost:9200/ads/ad/_search?q=zipCode:08001" | python -m json.tool

# Will retrieve only new ad added in this file, because is the only one with 08002
curl -XGET "http://localhost:9200/ads/ad/_search?q=zipCode:08002" | python -m json.tool

# Adding an element to an array using painless scripting
curl -XPOST "http://localhost:9200/ads/ad/10/_update" -d'
{
  "script": {
    "lang": "painless",
    "inline": "ctx._source.zipCode.addAll(params.zipCodes)",
    "params": {
      "zipCodes": ["36900", "15075"]
    }
  }
}' | python -m json.tool
