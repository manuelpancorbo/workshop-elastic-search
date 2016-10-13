#!/usr/bin/env bash

# Delete index if exists
curl -XDELETE "http://localhost:9200/ads"

# Create index
curl -XPUT "http://localhost:9200/ads"

# Create mapping
curl -XPUT -d '
{
    "properties": {
      "id": {
        "type": "integer"
      },
      "title" : {
        "type": "string",
        "analyzer": "spanish"
      },
      "body" : {
        "type": "string",
        "analyzer": "spanish"
      },
      "price" : {
        "type": "double"
      },
      "zipCode" : {
        "type": "string"
      },
      "location": {
        "fielddata": {
          "precision": "3m",
          "format": "compressed"
        },
        "type": "geo_point",
        "lat_lon": true,
        "geohash_prefix": true,
        "geohash_precision": "50m"
      }
    }
}
' "http://localhost:9200/ads/_mapping/ad" | python -m json.tool