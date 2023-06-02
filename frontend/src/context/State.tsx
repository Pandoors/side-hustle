import { useRouter } from "next/router";
import React, { createContext, useContext, useEffect, useState } from "react";
import toast from "react-hot-toast";

export const StateContext = createContext({});

export const useStateProvider = () => useContext<any>(StateContext);

export const StateProvider = ({ children }: { children: React.ReactNode }) => {
  const [jobOffers, setJobOffers] = useState<any>();
  const [offersCount, setOffersCount] = useState<number>(0);
  const [auth, setAuth] = useState<any>({ username: null });

  const router = useRouter();

  useEffect(() => {
   let obj;
    fetch("http://localhost:8080/api/v1/offer/list")
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        obj = data;
        setJobOffers(data);
        console.log(data);
      });

    fetch("http://localhost:8080/api/v1/offer/count")
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        obj = data;
        setOffersCount(data);
        console.log(data);
      });
  }, []);

  const networkStatusFilter = async (res: any) => {
    if (res.status < 300 && res.status >= 200) {
      return res.json();
    } else {
      try{
        if (res) {
          let data: any = await res.json();
          if (data) {
           
              toast.error(data.message);
            
          } else {
            toast.error("Błąd sieci");
          }
        }
      }catch(error){
        toast.error("Złe hasło lub login");
      }
      console.log(res)
      

      return null;
    }
  };

  const register = (obj: any) => {
    const params: any = {
      username: obj.login,
      email: obj.email,
      password: obj.password,
    };

    const options: any = {
      method: "POST",
      body: JSON.stringify(params),
      headers: {
        "Content-Type": "application/json",
      },
    };

    fetch("http://localhost:8080/api/v1/auth/signup", options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data && data.message) {
          toast.success(data.message);
          setInterval(() => {
            router.push("/login");
          }, 1000);
        }
      });
  };

  const login = (obj: any) => {
    const params: any = {
      username: obj.login,
      password: obj.password,
    };

    const options: any = {
      method: "POST",
      body: JSON.stringify(params),
      headers: {
        "Content-Type": "application/json",
      },
    };

    fetch("http://localhost:8080/api/v1/auth/signin", options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data  ) {
          toast.success("User logged in successfully");
          setAuth({ username: data.username });
          setInterval(() => {
            router.push("/");
          }, 1000);
        }
      });
  };

  const stateValue = {
    jobOffers: jobOffers,
    offersCount: offersCount,
    register: register,
    login: login,
    auth: auth,
    setAuth: setAuth,
  };

  return (
    <StateContext.Provider value={stateValue}>{children}</StateContext.Provider>
  );
};
