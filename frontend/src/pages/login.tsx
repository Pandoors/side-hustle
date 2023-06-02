import Head from 'next/head'
import Image from 'next/image'
import { Inter } from 'next/font/google'
import styles from '@/styles/Home.module.css'
import Filters from '@/components/filters/Filters'
import WorkOffers from '@/components/workOffers/WorkOffers'
import dynamic from 'next/dynamic';
import auth_banner from "../assets/images/auth_banner.png";
import { useFormik } from 'formik'
import loginSchema from '@/schemas/loginSchema'
import { useStateProvider } from '@/context/State'




export default function Login() {
 
  const {login} = useStateProvider()

  const form = useFormik({
    initialValues: {
      password: "",
      login: "",
    },
    validationSchema: loginSchema,
    onSubmit: async (values,) => {
      console.log(values)
      login(values)

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
                <input className='auth_input'  value={form.values.login}
                onChange={form.handleChange("login")} ></input>
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
        <div className='auth_action_button'>
            <div className='auth_action_button_text' onClick={()=>form.handleSubmit()}>Zaloguj się</div>
        </div>
        <div className='auth_description'>
            Nie masz konta? <span className='auth_description_highlited'>{" "} Zarejestruj się</span>
        </div>
    </div>
  )
}
