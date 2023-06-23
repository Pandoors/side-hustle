import Head from 'next/head'
import Image from 'next/image'
import { Inter } from 'next/font/google'
import styles from '@/styles/Home.module.css'
import Filters from '@/components/filters/Filters'
import WorkOffers from '@/components/workOffers/WorkOffers'
import dynamic from 'next/dynamic';
import auth_banner from "../assets/images/auth_banner.png";
import { useFormik } from 'formik'
import registerSchema from '@/schemas/registerSchema'
import { useStateProvider } from '@/context/State'
import { useRouter } from 'next/router'

export default function Register() {

    const state = useStateProvider()

    const Router = useRouter()

    const form = useFormik({
        initialValues: {
          email: "",
          password: "",
          login: "",
        },
        validationSchema: registerSchema,
        onSubmit: async (values,) => {
          console.log(values)
          state.register(values)
    
        },
      });
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
                <input className='auth_input' 
                value={form.values.login}
                onChange={form.handleChange("login")}
                ></input>
            </div>
        </div>
        <div className='auth_input_div'>
            <div className='auth_input_description'>
                Email
            </div>
            <div>
                <input className='auth_input' value={form.values.email} onChange={form.handleChange("email")} type="email"></input>
            </div>
        </div>
        <div className='auth_input_div'>
            <div className='auth_input_description'>
                Hasło
            </div>
            <div>
                <input className='auth_input' value={form.values.password} onChange={form.handleChange("password")} type='password'></input>
            </div>
        </div>
        <div className='auth_action_button'  onClick={()=>form.handleSubmit()} >
            <div className='auth_action_button_text'style={{position:'absolute', left: '17px'}}>Zarejestruj się</div>
        </div>
        <div className='auth_description'>
            Masz konto? <span className='auth_description_highlited' onClick={()=>Router.push("/login")}>{" "} Zaloguj się</span>
        </div>
    </div>
  )
}
