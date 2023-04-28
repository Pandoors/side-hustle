import React from "react";
import Image from "next/image";
import logo from '../../../assets/images/Logo.png'

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
    </div>
  );
}

export default Header;
