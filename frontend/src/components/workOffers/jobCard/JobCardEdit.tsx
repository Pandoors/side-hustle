import CustomButton from "@/components/buttons/CustomButton";
import { useStateProvider } from "@/context/State";
import { DatePicker, Form, Input, Radio } from "antd";
const { TextArea } = Input;
import moment from "moment";

import React from "react";
import toast from "react-hot-toast";
import { date } from "yup";

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

function JobCardEdit() {
  const [form] = Form.useForm();

  const dateFormat = "DD-MM-YYYY_HHmm";
  const state = useStateProvider()


  const handleDateChange = (date: any) => {
    if (date !== null) {
      // Check if a date has been selected
      const formattedDate = date.format(dateFormat);
      console.log(formattedDate);
      // Do something with formattedDate...
    }
  };

  const onFinish = (values: any) => {
    values.startDate = (values.startDate).format(dateFormat).toString()
    values.endDate =  (values.endDate).format(dateFormat).toString()
    console.log(values);
    preparetoAddOffer(values);
    //updateCustomersRequest(values);
    // void updateUser(values);
    // void addPartner(values);
    // form.resetFields();
    // hideModal(false);
  };

  const preparetoAddOffer = (values:any) => {
    if(state.latLangOfChoosenPlace.lat == undefined || state.latLangOfChoosenPlace.lng == undefined){
      toast.error("Wybierz lokalizację")
      return
    }
    const offer = {
      "wage": values.wage,
      "description": values.description,
      "fullName": values.fulName,
      "jobType": values.jobType,
      "startDate":    values.startDate,
      "endDate":    values.endDate,
      "location": {
        "city": "Kraków",
        "address": "31/2",
        "district": values.location,
        "latitude": state.latLangOfChoosenPlace.lat,
        "longitude": state.latLangOfChoosenPlace.lng
      }
    }
    state.addPost(offer)
  }

  return (
    <div className="job_card_without_hover" style={{ cursor: "default" }}>
      <Form
        name="form"
        form={form}
        style={{ maxWidth: 600 }}
        initialValues={{ remember: true }}
        onFinish={onFinish}
        autoComplete="off"
        layout="vertical"
      >
        <Form.Item
          style={{
            width: "570px",
            height: "70px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          label="Nazwa oferty"
          name="fulName"
          rules={[{ required: true, message: "Required" }]}
        >
          <Input style={{ width: "100%", height: "48px" }} />
        </Form.Item>
        <Form.Item
          label="Stawka za godzinę (zł/h)"
          name="wage"
          style={{
            width: "570px",
            height: "70px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          rules={[{ required: true, message: "Required" }]}
        >
          <Input type="number" style={{ width: "100%", height: "48px" }} />
        </Form.Item>
        <Form.Item
          rules={[{ required: true, message: "Required" }]}
          label="Dzielnica Krakowa"
          style={{
            width: "570px",
            height: "70px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          name="location"
        >
          <Input style={{ width: "100%", height: "48px" }} />
        </Form.Item>
        <Form.Item
          style={{
            width: "570px",
            height: "70px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          rules={[{ required: true, message: "Required" }]}
          label="Rodzaj pracy"
          name="jobType"
        >
          <Radio.Group>
            <Radio value={"Fizyczna"}>Fizyczna</Radio>
            <Radio value={"Umysłowa"}>Umysłowa</Radio>
          </Radio.Group>
        </Form.Item>
        <div
          style={{
            display: "flex",
            alignItems: "flex-start",
            flexDirection: "row",
          }}
        >
          <Form.Item
            style={{
              width: "250px",
              height: "90px",
              fontSize: "14px",
              fontWeight: 600,
              lineHeight: "20px",
              letterSpacing: "0.2px",
              color: "#121212",
            }}
            rules={[{ required: true, message: "Required" }]}
            label="Początek zlecenia"
            name="startDate"
          >
            <DatePicker
              placeholder="Wybierz datę"
              onChange={handleDateChange}
              format={dateFormat}
            />
          </Form.Item>
          <Form.Item
            style={{
              width: "250px",
              height: "90px",
              fontSize: "14px",
              fontWeight: 600,
              lineHeight: "20px",
              letterSpacing: "0.2px",
              color: "#121212",
              display: "flex",
            }}
            rules={[{ required: true, message: "Required" }]}
            label="Koniec zlecenia"
            name="endDate"
          >
            <DatePicker
              placeholder="Wybierz datę"
              onChange={handleDateChange}
              format={dateFormat}
            />
          </Form.Item>
        </div>

        <Form.Item
          style={{
            width: "570px",
            height: "70px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          rules={[{ required: true, message: "Required" }]}
          name="duration_time"
          label="Szacowany czas realizacji w godzinach"
        >
          <Input style={{ width: "100%", height: "48px" }} />
        </Form.Item>
        <Form.Item
          style={{
            width: "570px",
            height: "170px",
            fontSize: "14px",
            fontWeight: 600,
            lineHeight: "20px",
            letterSpacing: "0.2px",
            color: "#121212",
          }}
          rules={[{ required: true, message: "Required" }]}
          name="description"
          label="Opis"
        >
          <TextArea style={{ width: "100%", height: "130px" }} />
        </Form.Item>
      </Form>
      <CustomButton text="Dodaj ofertę" onClick={form.submit}></CustomButton>
    </div>
  );
}

export default JobCardEdit;
