import React, { ReactFragment } from "react";
import Offer from "./jobCard/JobCard";
import JobCard from "./jobCard/JobCard";
import { useStateProvider } from "@/context/State";
import { JsxElement } from "typescript";

interface offer {
  "offerId": Number,
  "description": string,
  "location": string,
  "startDate": string,
  "endDate": string,
  "jobType": string,
  "formattedDuration": string,
  "wage": string,
  "longitude": Number,
  "latitude": Number,
  "fullName": string
}


function WorkOffers() {
  const state = useStateProvider()
  const renderJobCards = ()=>{

    let jobCards: ReactFragment[]; 
    if(state.jobOffers && state.jobOffers.length > 0){
      jobCards = state.jobOffers.map((el:offer, id:number)=>{
        return <JobCard params={el}></JobCard>
    })
    return jobCards
    }
    return <></>
  }

  return (
    <div className="work_offers">
      <div
        style={{
          color: "#3B39A1",
          fontSize: "24px",
          fontWeight: "700",
          marginLeft: "80px",
          marginTop: "30px",
        }}
      >
        Oferty pracy ({state.offersCount})
      </div>
      <div className="job_cards">
        {renderJobCards()}
      </div>
      
    </div>
  );
}

export default WorkOffers;
