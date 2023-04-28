import React from "react";
import Image from "next/image";
import logo from '../../../assets/images/Location.png'

function JobCard() {
  return (
    <div className="job_card">
      <div className="job_card_title">
        <div className="job_card_title_text">Test</div>
        <div className="job_card_title_money">25zl/h</div>
      </div>
      <div className="job_card_badge_container">
        <div className="job_card_badge">
        <Image
        src={logo}
        alt="Picture of the author"
        width="20"
        height="20"
      />
          <div className="job_card_badge_text">Prądnik Biały</div>
        </div>
      </div>
    </div>
  );
}

export default JobCard;
