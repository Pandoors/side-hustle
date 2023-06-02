import Head from 'next/head'
import Image from 'next/image'
import { Inter } from 'next/font/google'
import styles from '@/styles/Home.module.css'
import Filters from '@/components/filters/Filters'
import WorkOffers from '@/components/workOffers/WorkOffers'
import dynamic from 'next/dynamic';
import auth_banner from "../assets/images/auth_banner.png";


const MapComponent = dynamic(() => import('@/components/streetMap/MapComponent'), {
  ssr: false, // Disable server-side rendering for the MapComponent
});
const inter = Inter({ subsets: ['latin'] })

export default function Login() {
  const markers = [
    { lat: 50.068693, lng: 19.923657 },
  
  ];
  return (
    <div className='login_page'>
        <div>
        <Image
            style={{marginBottom: '80px'}}
            src={auth_banner}
            alt="Picture of the author"
            width="203"
            height="191"
          />
        </div>
        <div className='auth_input_div'>
            <div className='auth_input_description'>
                Login
            </div>
            <div>
                <input className='auth_input'></input>
            </div>
        </div>
        <div className='auth_input_div'>
            <div className='auth_input_description'>
                Hasło
            </div>
            <div>
                <input className='auth_input' type='password'></input>
            </div>
        </div>
        <div className='auth_action_button'>
            <div className='auth_action_button_text'>Zaloguj się</div>
        </div>
        <div className='auth_description'>
            Nie masz konta? <span className='auth_description_highlited'>{" "} Zarejestruj się</span>
        </div>
    </div>
  )
}
