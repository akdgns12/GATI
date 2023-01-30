import { React } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, Container, TextField } from '@mui/material';
import { Link } from 'react-router-dom';

const contStyle = css`
  width : 300px;
  background-color: #f0f0f0;
  margin: 0 auto;
  margin-top: 50%;
  .input-form {
    widht: 80%;
    border-radius: 10px;
  }
  .login-btn {
    width: 100%;
    border-radius: 5px;
    margin-top: 5px;
  }
  .btn-group {
    margin-top: 10px;
  }
  .router-link {
    box-sizing: border-box;
    margin: 10px;
    display: inline-block;
    color: black;
    text-decoration: none;
    div {
      border-style: solid;
      border-width: 0px;
      border-color: #FF0000;
    }
  }
`;

const LoginFrom = () => {

  function doSth() {
    console.log("do sth");
  };

  return (
    <Container css={contStyle}>
      <Box component="form" onSubmit={doSth}>
        <TextField margin='normal' required fullWidth label="ID" size='small' />
        <TextField margin='normal' required fullWidth label="PASSWORD" size='small' />
        <Button type='submit' variant='outlined' color='inherit' className='login-btn'>Login</Button>
      </Box>
      <Box className="btn-group">
        <Link to="/signup" className='router-link'>
          <div>회원가입</div>
        </Link>
        <Link to="/findID" className='router-link'><div>ID 찾기</div></Link>
        <Link to="/findPW" className='router-link'><div>PW 찾기</div></Link>
      </Box>
    </Container>
  );
};

export default LoginFrom;