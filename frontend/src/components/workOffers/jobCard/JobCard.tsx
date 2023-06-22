import React from "react";
import Image from "next/image";
import location from "../../../assets/images/Location.png";
import Calendar from "../../../assets/images/Calendar.png";
import Hourglass from "../../../assets/images/Hourglass empty.png";
import Time from "../../../assets/images/Time.png";
import Work from "../../../assets/images/Work.png";

interface offer {
  offerId: Number;
  description: string;
  location: string;
  startDate: string;
  endDate: string;
  jobType: string;
  formattedDuration: string;
  wage: string;
  longitude: Number;
  latitude: Number;
  fullName: string;
}

function JobCard({ params }: { params: offer }) {
  return (
    <div className="job_card">
      <div className="job_card_title">
        <div className="job_card_title_text">{params.fullName}</div>
        <div className="job_card_title_money">{params.wage}</div>
      </div>
      <div className="job_card_badge_container">
        <div className="job_card_badge">
          <Image
            src={location}
            alt="Picture of the author"
            width="20"
            height="20"
          />
          <div className="job_card_badge_text">{params.location.split(" ")[0]}</div>
        </div>
        <div className="job_card_badge">
          <Image
            src={Work}
            alt="Picture of the author"
            width="20"
            height="20"
          />

          <div className="job_card_badge_text">{params.jobType}</div>
        </div>
      </div>
      <div className="job_card_badge_container">
        <div className="job_card_badge">
          <Image
            src={Hourglass}
            alt="Picture of the author"
            width="20"
            height="20"
          />
          <div className="job_card_badge_text">{params.formattedDuration ?? Math.floor(Math.random() * 100) + 1 +"h"}</div>
        </div>
        <div className="job_card_badge">
          <Image
            src={Calendar}
            alt="Picture of the author"
            width="20"
            height="20"
          />
          <div className="job_card_badge_text">{`${Math.floor(Math.random() * 31) + 1+"."+ (Math.floor(Math.random() * 12) +1)} - ${Math.floor(Math.random() * 31) + 1+"."+ (Math.floor(Math.random() * 12) +1)}`}</div>
        </div>
      </div>
    </div>
  );
}

export default JobCard;
