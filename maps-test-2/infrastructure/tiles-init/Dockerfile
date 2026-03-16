FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    wget \
    osmium-tool \
    && rm -rf /var/lib/apt/lists/*

# Use tilemaker from releases
RUN wget https://github.com/systemed/tilemaker/releases/download/v2.4.0/tilemaker-ubuntu-22.04.zip && \
    apt-get update && apt-get install -y unzip && \
    unzip tilemaker-ubuntu-22.04.zip && \
    mv build/tilemaker /usr/local/bin/ && \
    chmod +x /usr/local/bin/tilemaker && \
    rm -rf tilemaker-ubuntu-22.04.zip build

# Download openmaptiles config for tilemaker
RUN wget https://raw.githubusercontent.com/systemed/tilemaker/master/resources/config-openmaptiles.json -O /config.json && \
    wget https://raw.githubusercontent.com/systemed/tilemaker/master/resources/process-openmaptiles.lua -O /process.lua

# We will download Bolivia PBF, but since it's large and takes time, we will extract just cochabamba area.
# Cochabamba bbox approx: -66.3, -17.5, -65.9, -17.2

WORKDIR /data

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
