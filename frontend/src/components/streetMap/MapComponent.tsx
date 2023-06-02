// MapComponent.tsx
import React, { useEffect, useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';

// Fix for the missing marker icon issue
//delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-icon.png',
  iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-icon.png',
  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-shadow.png',
});

interface Coordinates {
  lat: number;
  lng: number;
  description?: string;
}

interface MapComponentProps {
  markers: Coordinates[];
}

const MapComponent: React.FC<MapComponentProps> = ({ markers }) => {
  const [currentLocation, setCurrentLocation] = useState<Coordinates | null>(null);

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setCurrentLocation({ lat: position.coords.latitude, lng: position.coords.longitude });
      },
      (error) => {
        console.error('Error obtaining location:', error);
      }
    );
  }, []);

  const defaultCenter: Coordinates = {
     lat: 50.068693, lng: 19.923657 ,
  };

  return (
      <MapContainer style={{ height: '740px', width: '90%', margin: "2rem 0rem 0rem 3rem" }} center={currentLocation || defaultCenter} zoom={13}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        {currentLocation && (
          <Marker position={currentLocation}>
            <Popup>You are here.</Popup>
          </Marker>
        )}
        {markers.map((marker, index) => (
          <Marker key={index} position={marker}>
            <Popup>{marker.description}</Popup>
          </Marker>
        ))}
      </MapContainer>
   
    
  );
};

export default MapComponent;
