import React, { createContext, useContext, useEffect, useState } from "react";

export const AuthContext = createContext({});

export const useAuthProvider = () => useContext<any>(AuthContext);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
//   const [jobOffers, setJobOffers] = useState<any>();

//   useEffect(() => {
//     let obj;
//     fetch("http://localhost:8080/api/v1/offer/list")
//       .then((res) =>  res.json())
//       .then((data) => {
//         console.log(data)
//         obj = data;
//         setJobOffers(data)
//         console.log(data)
//       });
//   }, []);

  return (
    <AuthContext.Provider value={"jobOffers"}>
      {children}
    </AuthContext.Provider>
  );
};
