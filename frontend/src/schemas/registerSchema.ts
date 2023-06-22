import * as yup from "yup";

const registerSchema = yup.object({
    email: yup.string().required().email(),
    password: yup.string().required(),
    login: yup.string().required()
});

export default registerSchema;
