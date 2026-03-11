#!/bin/bash
set -e

mkdir -p /data/styles/positron
mkdir -p /data/styles/dark-matter

if [ ! -f /data/bolivia.mbtiles ]; then
    echo "Downloading Bolivia OSM data..."
    wget -qO /tmp/bolivia-latest.osm.pbf https://download.geofabrik.de/south-america/bolivia-latest.osm.pbf

    echo "Extracting Cochabamba region to speed up processing..."
    osmium extract -b -66.3,-17.5,-65.9,-17.2 /tmp/bolivia-latest.osm.pbf -o /tmp/cochabamba.osm.pbf --overwrite

    echo "Generating mbtiles with tilemaker..."
    tilemaker --input /tmp/cochabamba.osm.pbf --output /data/bolivia.mbtiles --config /config.json --process /process.lua

    echo "Done generating mbtiles."
else
    echo "bolivia.mbtiles already exists, skipping generation."
fi

# Generate config.json for TileServer GL
cat << 'JSON_EOF' > /data/config.json
{
  "options": {
    "paths": {
      "root": "/data",
      "fonts": "fonts",
      "sprites": "sprites",
      "styles": "styles",
      "mbtiles": ""
    }
  },
  "styles": {
    "positron": {
      "style": "positron/style.json",
      "tilejson": {
        "bounds": [-66.3, -17.5, -65.9, -17.2]
      }
    },
    "dark-matter": {
      "style": "dark-matter/style.json",
      "tilejson": {
        "bounds": [-66.3, -17.5, -65.9, -17.2]
      }
    }
  },
  "data": {
    "v3": {
      "mbtiles": "bolivia.mbtiles"
    }
  }
}
JSON_EOF

echo "Initialization complete."
