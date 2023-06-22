import { AppProps } from 'next/app'
import React, { ReactNode, ReactPropTypes, useEffect, useState } from 'react'
import Header from './header/Header'
import { useRouter } from 'next/router'


type Props = {
    children: string | JSX.Element | JSX.Element[] | (() => JSX.Element)
  }

function Layout({children}:Props) {
  const [isHeader, setHeader] = useState(true)
  const router = useRouter()

  useEffect(()=>{
    if(router.pathname === '/login' || router.pathname === '/register'){
      setHeader(false)
    }else{
      setHeader(true)
    }

  })

  return (
    <>
    {isHeader?<Header/>:null}
    {children} 
    </>
  )
}

export default Layout