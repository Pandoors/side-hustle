import Head from 'next/head'
import Image from 'next/image'
import { Inter } from 'next/font/google'
import styles from '@/styles/Home.module.css'
import Filters from '@/components/filters/Filters'
import WorkOffers from '@/components/workOffers/WorkOffers'
import dynamic from 'next/dynamic';
import { useEffect, useState } from 'react'
import { useStateProvider } from '@/context/State'

const MapComponent = dynamic(() => import('@/components/streetMap/MapComponent'), {
  ssr: false, // Disable server-side rendering for the MapComponent
});
const inter = Inter({ subsets: ['latin'] })

export default function Home() {
  const [markers, setMarkers] = useState([{ lat: 50.068693, lng: 19.923657 },]);

  const state = useStateProvider()
  useEffect( ()=>{

    let markersList : any[] = []; 
    if(state.jobOffers && state.jobOffers.length > 0){
      markersList = state.jobOffers.map((el:any, id:number)=>{
        return {lat: el.latitude, lng: el.longitude, description: el.fullName}
    })
    }
    console.log(markersList)
    setMarkers(markersList)
  }, [state])

  return (
    <>
     <Filters/>
      <div className='work_offers_and_map'>
        <WorkOffers/>
        <MapComponent markers={markers}/>
      </div>

       
    </>
  )
}
