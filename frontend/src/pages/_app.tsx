import Layout from "@/components/layouts/Layout";
import { StateProvider } from "@/context/State";
import "@/styles/globals.css";
import type { AppProps } from "next/app";
import Head from "next/head";
import toast, { Toaster } from 'react-hot-toast';
export default function App({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <title>Side Hustle</title>
        <meta name="description" content="Side Hustle" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap"
          rel="stylesheet"
        />
      </Head>
     <Toaster></Toaster>
      <StateProvider>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </StateProvider>
    </>
  );
}
