import { useEffect, useRef, useState } from 'react';
import maplibregl, { Map as MapLibreMap } from 'maplibre-gl';
import 'maplibre-gl/dist/maplibre-gl.css';

interface MapContainerProps {
  theme: 'light' | 'dark';
}

const TILESERVER_URL = import.meta.env.VITE_TILESERVER_URL || 'http://localhost:8080';
const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8000';

const MapContainer = ({ theme }: MapContainerProps) => {
  const mapContainerRef = useRef<HTMLDivElement>(null);
  const mapRef = useRef<MapLibreMap | null>(null);
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const [pois, setPois] = useState<any>(null);
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const poisRef = useRef<any>(null);

  useEffect(() => {
    poisRef.current = pois;
  }, [pois]);

  useEffect(() => {
    fetch(`${API_URL}/api/pois`)
      .then(res => res.json())
      .then(data => setPois(data))
      .catch(err => console.error("Error fetching POIs:", err));
  }, []);

  useEffect(() => {
    if (!mapContainerRef.current) return;

    const styleUrl = theme === 'light'
      ? `${TILESERVER_URL}/styles/positron/style.json`
      : `${TILESERVER_URL}/styles/dark-matter/style.json`;

    if (!mapRef.current) {
      mapRef.current = new maplibregl.Map({
        container: mapContainerRef.current,
        style: styleUrl,
        center: [-66.1568, -17.3938],
        zoom: 12
      });

      mapRef.current.on('load', () => {
        if (!mapRef.current) return;

        mapRef.current.addSource('pois', {
          type: 'geojson',
          data: {
            type: "FeatureCollection",
            features: []
          }
        });

        mapRef.current.addLayer({
          id: 'pois-layer',
          type: 'circle',
          source: 'pois',
          paint: {
            'circle-radius': 8,
            'circle-color': '#ff0000',
            'circle-stroke-width': 2,
            'circle-stroke-color': '#ffffff'
          }
        });

        mapRef.current.addLayer({
          id: 'pois-labels',
          type: 'symbol',
          source: 'pois',
          layout: {
            'text-field': ['get', 'name'],
            'text-variable-anchor': ['top', 'bottom', 'left', 'right'],
            'text-radial-offset': 1,
            'text-justify': 'auto'
          },
          paint: {
            'text-color': theme === 'light' ? '#000000' : '#ffffff',
            'text-halo-color': theme === 'light' ? '#ffffff' : '#000000',
            'text-halo-width': 2
          }
        });

        if (poisRef.current) {
          (mapRef.current.getSource('pois') as maplibregl.GeoJSONSource).setData(poisRef.current);
        }
      });
    } else {
      mapRef.current.setStyle(styleUrl);

      mapRef.current.once('style.load', () => {
        if (!mapRef.current) return;

        mapRef.current.addSource('pois', {
          type: 'geojson',
          data: poisRef.current || { type: "FeatureCollection", features: [] }
        });

        mapRef.current.addLayer({
          id: 'pois-layer',
          type: 'circle',
          source: 'pois',
          paint: {
            'circle-radius': 8,
            'circle-color': '#ff0000',
            'circle-stroke-width': 2,
            'circle-stroke-color': '#ffffff'
          }
        });

        mapRef.current.addLayer({
          id: 'pois-labels',
          type: 'symbol',
          source: 'pois',
          layout: {
            'text-field': ['get', 'name'],
            'text-variable-anchor': ['top', 'bottom', 'left', 'right'],
            'text-radial-offset': 1,
            'text-justify': 'auto'
          },
          paint: {
            'text-color': theme === 'light' ? '#000000' : '#ffffff',
            'text-halo-color': theme === 'light' ? '#ffffff' : '#000000',
            'text-halo-width': 2
          }
        });
      });
    }
  }, [theme]);

  useEffect(() => {
    if (mapRef.current && pois) {
      const source = mapRef.current.getSource('pois') as maplibregl.GeoJSONSource;
      if (source) {
        source.setData(pois);
      }
    }
  }, [pois]);

  return <div ref={mapContainerRef} className="map-container" />;
};

export default MapContainer;
