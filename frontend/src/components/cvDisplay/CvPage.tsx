import React from "react";
import { Form, Input, Button } from "antd";
import CustomButton from "../buttons/CustomButton";
import { useStateProvider } from "@/context/State";

const CvPage = () => {
  const state = useStateProvider();
  const [form] = Form.useForm();

  const onFinish = (values: any) => {
    console.log("Received values of form: ", values);
    state.addCv(values);
  };

  return (
    <div className="cv_page" style={{margin: "auto", textAlign: 'center', width: '400px'}}>
    <Form
      name="form"
      onFinish={onFinish}
      form={form}
      layout="vertical"
      style={{ maxWidth: "600px" }}
    >
      <Form.Item
        style={{ width: "400px" }}
        name="firstName"
        rules={[{ required: true, message: "Please input your first name!" }]}
      >
        <Input style={{ width: "400px" }} placeholder="First Name" />
      </Form.Item>

      <Form.Item
        name="lastName"
        rules={[{ required: true, message: "Please input your last name!" }]}
      >
        <Input placeholder="Last Name" />
      </Form.Item>

      <Form.Item
        name="email"
        rules={[
          { type: "email", message: "The input is not a valid E-mail!" },
          { required: true, message: "Please input your E-mail!" },
        ]}
      >
        <Input placeholder="Email" />
      </Form.Item>

      <Form.Item
        name="phoneNumber"
        rules={[{ required: true, message: "Please input your phone number!" }]}
      >
        <Input placeholder="Phone Number" />
      </Form.Item>

      <Form.Item
        name="education"
        rules={[{ required: true, message: "Please input your education!" }]}
      >
        <Input placeholder="Education" />
      </Form.Item>

      <Form.Item>
        <CustomButton text="Zapisz" style={{width: '100px'}} onClick={form.submit} />
      </Form.Item>
    </Form>
    </div>
  );
};

export default CvPage;
