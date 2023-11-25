import React from 'react';
import { useFormContext } from 'react-hook-form';
import { isValid, findInputErrors } from "../services/formCheck.js"
import { TextField } from '@mui/material';

const Input = (props) => {
    const {label, id, validation} = props;
    const {
        register,
        formState: { errors },
    } = useFormContext()

    const inputError = findInputErrors(errors, label);
    const isInvalid = !isValid(inputError);

    return (
        <TextField
            variant="standard"
            fullWidth
            style={{ margin: "10px 0" }}
            id={id}
            type="number"
            label={label}
            InputLabelProps={{ shrink: true }}
            helperText={inputError.error?.message}
            error = {isInvalid}
            {...register(label, validation)}
        />
    );
};

export default Input;