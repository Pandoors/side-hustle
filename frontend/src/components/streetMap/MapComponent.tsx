// MapComponent.tsx
import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L, { LeafletMouseEvent } from "leaflet";
import { useStateProvider } from "@/context/State";

// Fix for the missing marker icon issue
//delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-icon.png",
  iconUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-icon.png",
  shadowUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.0.0/images/marker-shadow.png",
});

interface Coordinates {
  lat: number;
  lng: number;
  description?: string;
}

interface MapComponentProps {
  markers: Coordinates[];
}

import { useMapEvents } from "react-leaflet";
import MapSetLocationComponent from "./MapSetLocationComponent";

const MapComponent: React.FC<MapComponentProps> = ({ markers }) => {
  const [currentLocation, setCurrentLocation] = useState<Coordinates | null>(
    null
  );
  const state = useStateProvider();

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setCurrentLocation({
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        });
      },
      (error) => {
        console.error("Error obtaining location:", error);
      }
    );
  }, []);

  const renderJobOffersMarkers = () => {
    if (
      state.jobOffers &&
      state.jobOffers.length > 0 &&
      !state.isCreateOfferJobCard
    ) {
      return state.jobOffers.map((el: any, id: number) => {
        return (
          <Marker key={id} position={{ lat: el.latitude, lng: el.longitude }}>
            <Popup>{el.description}</Popup>
          </Marker>
        );
      });
    }
    return [];
  };

  const defaultCenter: Coordinates = {
    lat: 50.068693,
    lng: 19.923657,
  };

  return (
    <div style={{width: '100%'}}>
      {state.isCreateOfferJobCard?<div
        style={{
          width: "100%",
          fontSize: "16px",
          color: "#3B39A1",
          fontFamily: "Poppins",
          marginTop: '2rem'
        }}
      >
        Wybierz lokalizację na mapie
      </div>:null}
      
      <MapContainer
        style={{ height: "740px", width: "90%", margin: "1rem 2rem 2rem 0rem" }}
        center={currentLocation || defaultCenter}
        zoom={13}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <MapSetLocationComponent />
        {currentLocation && (
          <Marker position={currentLocation}>
            <Popup>You are here.</Popup>
          </Marker>
        )}
        {state.latLangOfChoosenPlace && (
          <Marker position={state.latLangOfChoosenPlace}>
            <Popup>
              Selected Location: Lat - {state.latLangOfChoosenPlace.lat}, Lng -{" "}
              {state.latLangOfChoosenPlace.lng}
            </Popup>
          </Marker>
        )}
        {renderJobOffersMarkers()}
      </MapContainer>
    </div>
  );
};

export default MapComponent;
