import { useRouter } from "next/router";
import React, { createContext, useContext, useEffect, useState } from "react";
import toast from "react-hot-toast";

export const StateContext = createContext({});

export const useStateProvider = () => useContext<any>(StateContext);

export const StateProvider = ({ children }: { children: React.ReactNode }) => {
  const [jobOffers, setJobOffers] = useState<any>();
  const [offersCount, setOffersCount] = useState<number>(0);
  const [auth, setAuth] = useState<any>({
    username: null,
    accessToken: null,
    role: null,
    refreshToken: null,
  });
  const [currentOffer, setCurrentOffer] = useState<number>(-1);

  /// edit offer card
  const [isCreateOfferJobCard, setIsCreateOfferJobCard] = useState(false);
  const [latLangOfChoosenPlace, setLatLangOfChoosenPlace] = useState<any>({
    lat: 0,
    lng: 0,
  });

  const [isCvBeingAdded, setIsCvBeingAdded] = useState<boolean>(false);

  const defaultLink: string = "http://localhost:8080/api/";

  const router = useRouter();

  useEffect(() => {
    if (auth.role == "PROVIDER") {
      const options: any = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.accessToken,
        },
      };
      fetch(`${defaultLink}v1/offer/list/personal`, options)
        .then((res) => res.json())
        .then((data) => {
          console.log(data);
          setJobOffers(data);
          console.log(data);
        });
    } else {
      fetch(`${defaultLink}v1/offer/list?size=` + 13 + "&offset=0")
        .then((res) => res.json())
        .then((data) => {
          console.log(data);
          setJobOffers(data);
          console.log(data);
        });
    }
  }, [auth]);

  useEffect(() => {
    fetch(`${defaultLink}v1/offer/list?size=` + 13 + "&offset=0")
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setJobOffers(data);
        console.log(data);
      });

    fetch(`${defaultLink}v1/offer/count`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setOffersCount(data);
        console.log(data);
      });
  }, []);

  const networkStatusFilter = async (res: any) => {
    if (res.status < 300 && res.status >= 200) {
      return res.json();
    } else {
      try {
        if (res) {
          let data: any = await res.json();
          if (data) {
            toast.error(data.message);
          } else {
            toast.error("Błąd sieci");
          }
        }
      } catch (error) {
        toast.error("Złe hasło lub login");
      }
      console.log(res);

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

    fetch(`${defaultLink}v1/auth/signup`, options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data && data.message) {
          toast.success(data.message);
          setTimeout(() => {
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

    fetch(`${defaultLink}v1/auth/signin`, options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data) {
          toast.success("User logged in successfully");
          setAuth({
            username: data.username,
            accessToken: data.accessToken,
            role: data.roleLevel,
            refreshToken: data.refreshToken,
          });
          setTimeout(() => {
            router.push("/");
          }, 1000);
        }
      });
  };

  const addPost = (obj: any) => {
    const params: any = obj;

    const options: any = {
      method: "POST",
      body: JSON.stringify(params),
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + auth.accessToken,
      },
    };

    fetch(`${defaultLink}v1/offer/new`, options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data && data.message) {
          console.log(data);
          toast.success(data.message);
          setIsCreateOfferJobCard(false);
        }
      });
  };

  const removePost = (id: any) => {
    const params: any = {
      offerId: id,
    };

    const options: any = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + auth.accessToken,
      },
    };

    fetch(`${defaultLink}v1/offer/remove/` + id, options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data && data.message) {
          console.log(data);
          toast.success(data.message);
          setCurrentOffer(-1);
        }
      });
  };

  const addCv = (obj: any) => {
    const params: any = obj;

    const options: any = {
      method: "POST",
      body: JSON.stringify(params),
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + auth.accessToken,
      },
    };

    fetch(`${defaultLink}v1/cv/new`, options)
      .then((res) => {
        return networkStatusFilter(res);
      })
      .then((data: any) => {
        if (data && data.message) {
          console.log(data);
          toast.success(data.message);
          setCurrentOffer(-1);
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
    currentOffer: currentOffer,
    setCurrentOffer: setCurrentOffer,
    addPost: addPost,
    removePost: removePost,
    addCv: addCv,
    isCreateOfferJobCard: isCreateOfferJobCard,
    setIsCreateOfferJobCard: setIsCreateOfferJobCard,
    latLangOfChoosenPlace: latLangOfChoosenPlace,
    setLatLangOfChoosenPlace: setLatLangOfChoosenPlace,
    isCvBeingAdded: isCvBeingAdded,
    setIsCvBeingAdded: setIsCvBeingAdded,
  };

  return (
    <StateContext.Provider value={stateValue}>{children}</StateContext.Provider>
  );
};
