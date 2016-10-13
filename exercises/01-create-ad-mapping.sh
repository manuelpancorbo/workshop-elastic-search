#!/usr/bin/env bash

# Delete index if exists
curl -XDELETE "http://localhost:9200/ads"

# Create index
curl -XPUT "http://localhost:9200/ads"

# Add an alias
curl -XPOST 'http://localhost:9200/_aliases' -d '
{
    "actions" : [
        { "add" : { "index" : "ads", "alias" : "alias-for-ads" } }
    ]
}' | python -m json.tool

# Short alias adding
curl -XPUT 'http://localhost:9200/ads/_alias/old-ads'

# Creating an index 2015 ads
curl -XPUT "http://localhost:9200/ads-2015"

# Changing the alias old-ads for pointing to the ads-2015 index.
curl -XPOST 'http://localhost:9200/_aliases' -d '
{
    "actions" : [
        { "remove" : { "index" : "ads", "alias" : "old-ads" } },
        { "add" : { "index" : "ads-2015", "alias" : "old-ads" } }
    ]
}'


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

#Getting created mapping
curl -XGET "http://localhost:9200/ads/_mapping/ad" | python -m json.tool