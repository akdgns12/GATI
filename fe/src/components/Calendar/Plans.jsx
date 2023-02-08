import {React, useState}  from 'react'
import { useSelector, useDispatch } from 'react-redux'
import PlanCard from './PlanCard';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Grid } from '@mui/material';
export default function Plans(props) {


  return (
    <Grid container>
      {props.planData.map((plan, index) => {
        
        return <PlanCard key={index} plan={plan}/>
      })}
    </Grid>
    // <div>
    //   <div>hi</div>
    //   {/* <div>{props.planData[0]}</div> */}
    // </div>
  );
  
}
