import * as yup from "yup";

const registerSchema = yup.object({
    password: yup.string().required(),
    login: yup.string().required()
});

export default registerSchema;
