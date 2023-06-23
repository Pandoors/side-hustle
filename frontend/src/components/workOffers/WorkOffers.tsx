import React, { ReactFragment } from "react";
import Offer from "./jobCard/JobCard";
import JobCard from "./jobCard/JobCard";
import { useStateProvider } from "@/context/State";
import { JsxElement } from "typescript";
import JobCardDescription from "./jobCard/extensions/JobCardDescription";
import KeyboardBackspace from "../../assets/images/keyboard-backspace.png";
import Image from "next/image";
import CustomButton from "../buttons/CustomButton";
import JobCardEdit from "./jobCard/JobCardEdit";

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

function WorkOffers() {
  const state = useStateProvider();

  const setCreateOfferJobCard = () => {
    state.setIsCreateOfferJobCard(true);
  } 

  const renderJobCards = () => {
    let jobCards: ReactFragment[];
    let bottomElements: JSX.Element[] | undefined;
    let topElements: JSX.Element[] | undefined;
    if(state.isCreateOfferJobCard){
      return <JobCardEdit/>
    }
    if (state.currentOffer > 0 && state.jobOffers) {
      bottomElements = [
        <JobCardDescription
          params={
            state.jobOffers[
              state.jobOffers.findIndex(
                (el: any) => el.offerId == state.currentOffer
              )
            ]
          }
        ></JobCardDescription>,
      ];
      if (state.auth.role == "CUSTOMER") {
        bottomElements.push(<CustomButton text="Aplikuj" ></CustomButton>);
      }
    }

    if (state.jobOffers && state.jobOffers.length > 0) {
      jobCards = state.jobOffers.map((el: offer, id: number) => {
        if (state.currentOffer > 0) {
          if (el.offerId == state.currentOffer) {
            return (
              <JobCard params={el} bottomElements={bottomElements}></JobCard>
            );
          }
        } else {
          return (
            <JobCard params={el} bottomElements={bottomElements}></JobCard>
          );
        }
      });
      return jobCards;
    }
    return <></>;
  };

  return (
    <div className="work_offers">
      {state.currentOffer < 0 &&
      state.jobOffers &&
      state.auth.role != "PROVIDER" ? (
        <div
          style={{
            color: "#3B39A1",
            fontSize: "24px",
            fontWeight: "700",
            marginLeft: "80px",
            marginTop: "30px",
          }}
        >
          Oferty pracy( {state.jobOffers.length} )
        </div>
      ) : state.auth.role == "PROVIDER" && state.currentOffer < 0 && !state.isCreateOfferJobCard ? (
        <div
          style={{
            color: "#3B39A1",
            fontSize: "24px",
            fontWeight: "700",
            marginLeft: "80px",
            marginTop: "30px",
            display: "flex",
            flexDirection: "row",
            width: "618px",
          }}
        >
          <span style={{ marginTop: "20px" }}>
            Moje aktualne oferty pracy( {state.jobOffers.length} )
          </span>
          <div style={{ marginLeft: '70px'}}>
            <CustomButton text="Dodaj ofertę" onClick={setCreateOfferJobCard} ></CustomButton>
          </div>
        </div>
      ) : (
        <div
          style={{
            marginLeft: "80px",
            marginTop: "30px",
            display: "flex",
            flexDirection: "row",
            cursor: "pointer",
          }}
          onClick={() => {
            state.setCurrentOffer(-1);
            state.setIsCreateOfferJobCard(false);
          }}
        >
          <div style={{ maxHeight: "24px", marginTop: "0px" }}>
            <Image
              src={KeyboardBackspace}
              alt="Picture of the author"
              width="24"
              height="24"
            />
          </div>
          <div style={{ display: "inline-block", maxHeight: "24px" }}>
            <span
              className="back_to_offer_list_text"
              style={{
                fontSize: "16px",
                fontWeight: "300",
                marginLeft: "10px",
              }}
            >
              wróc do listy
            </span>
          </div>
        </div>
      )}
      {state.auth.role == "PROVIDER" && state.currentOffer > 0 ? (
        <div
          style={{
            marginLeft: "100px",
            marginTop: "30px",
            display: "flex",
            flexDirection: "row",
            cursor: "pointer",
          }}
        >
          <CustomButton text="Usuń" oppositeColors={true}></CustomButton>
          <CustomButton
            text="Zakończ rekrutację"
            oppositeColors={true}
          ></CustomButton>
          <CustomButton text="Edytuj" oppositeColors={true}></CustomButton>
          <CustomButton text="Zobacz CV" oppositeColors={false}></CustomButton>
        </div>
      ) : null}
      <div className="job_cards">{renderJobCards()}</div>
    </div>
  );
}

export default WorkOffers;
