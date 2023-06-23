import { useStateProvider } from '@/context/State';
import React from 'react'

import { useMapEvents } from 'react-leaflet'


function MapSetLocationComponent() {
    const state = useStateProvider();


    const map = useMapEvents({
        click: (event) => {
          state.setLatLangOfChoosenPlace({
            lat: event.latlng.lat,
            lng: event.latlng.lng,
          });
          console.log("Selected Location: ", event.latlng);
        },
      });

  return null
}

export default MapSetLocationComponent