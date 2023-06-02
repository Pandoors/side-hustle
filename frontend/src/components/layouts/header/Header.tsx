import React from "react";
import Image from "next/image";
import logo from "../../../assets/images/Logo.png";
import userLogo from "../../../assets/images/Vector.png"
import Link from "next/link";
import { useStateProvider } from "@/context/State";

function Header() {
  const { auth, setAuth } = useStateProvider();

  return (
    <div className="header">
      <Image
        src={logo}
        alt="Picture of the author"
        width="115"
        height="24"
        style={{ marginLeft: "64px", marginTop: "20px" }}
      />
      {auth.username ? (
        <div className="auth_header_buttons">
           <Image
        src={userLogo}
        alt="Picture of the author"
        width="27"
        height="27"
        style={{  }}
      />
          <div className="auth_header_username">
            {auth.username}
          </div>
          <div className="header_button" onClick={()=>{setAuth({username: null})}}>
            Wyloguj
          </div>
        </div>
      ) : (
        <div className="auth_header_buttons">
          <div className="header_button">
            <Link href={"/login"}>Logowanie</Link>
          </div>
          <div className="header_button">
            <Link href={"/register"}>Rejestracja</Link>
          </div>
        </div>
      )}
    </div>
  );
}

export default Header;
