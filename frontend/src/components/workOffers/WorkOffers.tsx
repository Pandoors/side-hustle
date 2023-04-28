import React from "react";
import Offer from "./jobCard/JobCard";
import JobCard from "./jobCard/JobCard";

function WorkOffers() {
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
        Oferty pracy (2137)
      </div>
      <div className="job_cards">
      <JobCard></JobCard>
      <JobCard></JobCard>
      <JobCard></JobCard>
      <JobCard></JobCard>

      <JobCard></JobCard>

      <JobCard></JobCard>

      <JobCard></JobCard>

      </div>
      
    </div>
  );
}

export default WorkOffers;
