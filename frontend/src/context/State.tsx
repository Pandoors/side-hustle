import React, { createContext, useContext, useEffect, useState } from "react";

export const StateContext = createContext({});

export const useStateProvider = () => useContext<any>(StateContext);

export const StateProvider = ({ children }: { children: React.ReactNode }) => {
  const [jobOffers, setJobOffers] = useState<any>();
  const [offersCount, setOffersCount] = useState<number>(0);
  const [auth, setAuth] = useState<any>({email: null, username:null});

  useEffect(() => {
    let obj;
    fetch("http://localhost:8080/api/v1/offer/list")
      .then((res) =>  res.json())
      .then((data) => {
        console.log(data)
        obj = data;
        setJobOffers(data)
        console.log(data)
      });

      fetch("http://localhost:8080/api/v1/offer/count")
      .then((res) =>  res.json())
      .then((data) => {
        console.log(data)
        obj = data;
        setOffersCount(data)
        console.log(data)
      });
  }, []);

  const register = (obj:any) => {
    const params:any = {
      ...obj, roles:[], role: []
  };
  const options:any = {
      method: 'POST',
      body: params
  };
  console.log(options)
    fetch("http://localhost:8080/api/v1/auth/signup", options)
    .then((res) =>  res.json())
    .then((data) => {
      console.log(data)
      obj = data;
      setOffersCount(data)
      console.log(data)
    });
  }

  return (
    <StateContext.Provider value={{"jobOffers": jobOffers, "offersCount": offersCount, "register": register}}>
      {children}
    </StateContext.Provider>
  );
};
