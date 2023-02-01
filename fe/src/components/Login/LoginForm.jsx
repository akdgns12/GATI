import { React } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, Container, TextField } from "@mui/material";
import { Link } from "react-router-dom";

const contStyle = css`
  width: 300px;
  background-color: rgba(255, 255, 255, 0.75);
  margin: 0 auto;
  margin-top: 50vh;
  border-radius: 10px;
  .input-form {
    widht: 80%;
    border-radius: 10px;
  }
  .login-btn {
    width: 100%;
    border-radius: 5px;
    margin-top: 10px;
  }
  .btn-group {
    margin-top: 15px;
    width: 100%;
    border-style: solid;
    border-width: 1px;
    border-radius: 5px;
    border-color: #c8c8c8;
  }
  .router-link {
    box-sizing: border-box;
    margin: 10px;
    display: inline-block;
    color: gray;
    text-decoration: none;
    div {
      border-style: solid;
      border-width: 0px;
      border-color: #ff0000;
    }
  }
`;

const LoginFrom = () => {
  function doSth() {
    console.log("do sth");
    alert("Incorrenct ID or PW");
  }

  return (
    <Container css={contStyle}>
      <Box component="form" onSubmit={doSth}>
        <TextField
          margin="normal"
          required
          fullWidth
          label="ID"
          size="small"
          autoFocus
        />
        <TextField
          margin="normal"
          required
          fullWidth
          label="PASSWORD"
          type="password"
          size="small"
        />
        <Button
          type="submit"
          variant="outlined"
          color="inherit"
          className="login-btn"
        >
          Login
        </Button>
      </Box>
      <Box className="btn-group">
        <Link to="/login/signup" className="router-link">
          <div>회원가입</div>
        </Link>
        <Link to="/login/findID" className="router-link">
          <div>ID 찾기</div>
        </Link>
        <Link to="/login/findPW" className="router-link">
          <div>PW 찾기</div>
        </Link>
      </Box>
    </Container>
  );
};

export default LoginFrom;
