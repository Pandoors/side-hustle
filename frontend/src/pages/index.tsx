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
  const markers = [
    { lat: 50.068693, lng: 19.923657 },
    { lat: 50.18693, lng: 19.923657 },
    { lat: 50.168693, lng: 19.923657 },
    { lat: 50.128693, lng: 19.973657 },
    { lat: 50.028693, lng: 19.943657 },
  ];
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
