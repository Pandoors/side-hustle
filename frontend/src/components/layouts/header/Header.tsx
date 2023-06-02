import React from "react";
import Image from "next/image";
import logo from '../../../assets/images/Logo.png'
import Link from "next/link";

function Header() {
  return (
    <div className="header">
      <Image
        src={logo}
        alt="Picture of the author"
        width="115"
        height="24"
        style={{marginLeft:'64px', marginTop:'20px'}}
      />
      <div className="auth_header_buttons">
        <div className="header_button"><Link href={"/login"}>Logowanie</Link></div>
        <div className="header_button"><Link href={"/register"}>Rejestracja</Link></div>
      </div>
    </div>
  );
}

export default Header;
