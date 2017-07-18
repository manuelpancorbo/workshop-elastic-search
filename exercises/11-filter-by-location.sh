#!/usr/bin/env bash

# Querying by lat,long
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool" : {
      "filter": {
        "geo_distance" : {
          "location" : [ 2.1677398681640625, 41.37794494628906 ],
          "distance" : "2000m",
          "distance_type" : "plane",
          "optimize_bbox" : "indexed"
        }
      }
    }
  },
  "sort" : [
    {
      "_geo_distance" : {
        "location" : [ {
          "lat" : 41.37794494628906,
          "lon" : 2.1677398681640625
        } ],
        "unit" : "m"
      }
    },
    {
      "price": "asc"
    },
    {
      "id": "desc"
    }
  ]
}
' | python -m json.tool

# Querying by geohash
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool" : {
      "filter": {
        "geo_distance" : {
          "location" : "sp3e3ks",
          "distance" : "2000m",
          "distance_type" : "plane",
          "optimize_bbox" : "indexed",
          "_cache" : true
        }
      }
    }
  },
  "sort" : [
    {
      "_geo_distance" : {
        "location" : "sp3e3ks",
        "unit" : "m"
      }
    },
    {
      "price": "asc"
    },
    {
      "id": "desc"
    }
  ]
}
' | python -m json.tool

# Querying by geo bounding box
curl -XGET "http://localhost:9200/ads/ad/_search" -d '
{
  "query": {
    "bool": {
      "filter": {
        "geo_bounding_box" : {
          "location" : {
            "top_left" : {
              "lat" : 41.40,
              "lon" : 2.15
            },
            "bottom_right" : {
              "lat" : 41.30,
              "lon" : 2.20
            }
          }
        }
      }
    }
  },
  "sort" : [
    {
      "_geo_distance" : {
        "location" : [ {
          "lat" : 41.37794494628906,
          "lon" : 2.1677398681640625
        } ],
        "unit" : "m"
      }
    },
    {
      "price": "asc"
    },
    {
      "id": "desc"
    }
  ]
}
' | python -m json.tool