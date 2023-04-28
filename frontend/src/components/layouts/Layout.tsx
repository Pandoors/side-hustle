import { AppProps } from 'next/app'
import React, { ReactNode, ReactPropTypes } from 'react'
import Header from './header/Header'


type Props = {
    children: string | JSX.Element | JSX.Element[] | (() => JSX.Element)
  }

function Layout({children}:Props) {
  return (
    <>
    <Header/>
    {children} 
    </>
  )
}

export default Layout