#!/usr/bin/env bash

# Create index
curl -XPUT "http://default.dm:9200/ads"

# Create mapping
curl -XPUT -d '
{
    "properties": {
      "id": {
        "type": "integer"
      },
      "title" : {
        "type": "string"
      },
      "body" : {
        "type": "string"
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
' "http://default.dm:9200/ads/_mapping/ad"