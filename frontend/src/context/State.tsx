import React, { createContext, useContext, useEffect, useState } from "react";

export const StateContext = createContext({});

export const useStateProvider = () => useContext<any>(StateContext);

export const StateProvider = ({ children }: { children: React.ReactNode }) => {
  const [jobOffers, setJobOffers] = useState<any>();

  useEffect(() => {
    // let obj;
    // fetch("http://localhost:8080/api/v1/offer/list")
    //   .then((res) => res.json())
    //   .then((data) => {
    //     obj = data;
    //   });
    // console.log(obj)
  }, []);

  return (
    <StateContext.Provider value={{ jobOffers }}>
      {children}
    </StateContext.Provider>
  );
};
