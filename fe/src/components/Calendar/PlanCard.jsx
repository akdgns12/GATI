import React from 'react'
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import DeleteIcon from '@mui/icons-material/Delete';
import httpClient from '../../utils/axios';
import { Grid } from '@mui/material';

import { useNavigate } from 'react-router';


export default function PlanCard(props) {

  const plan = props.plan;
  const navigate = useNavigate()

  async function deletePlan(id, success, fail) {
    const response = await httpClient.delete(`/plan/${id}`).then(success).catch(fail)
    return response
  }

  function handleDeletePlan() {
    const deletePlanDatas = async () => {
      await deletePlan(plan.id, (response) => {return response.data}, (e) => {console.log(e)})
    };
    deletePlanDatas();
    navigate(0)
    
  }

  return (
      <Grid item xs={6}>
        <Grid xs={8} value= 'sivla' sx={{m: 2, backgroundColor: '#685DD833'}} variant="outlined">
            <Typography sx={{ fontSize: 20 }} color="text.secondary" gutterBottom>
              {plan.title}
              <br />
              {plan.startDate.split("T")[0]}
              <br />
              {plan.endDate.split("T")[0]}
            </Typography>
          <Grid display='flex' justifyContent='start'>
            <DeleteIcon onClick={handleDeletePlan} style={{cursor: "pointer" }}/>
          </Grid>
        </Grid>
        {/* <Grid display='flex' justifyContent='start'>
          <DeleteIcon/>
        </Grid> */}
      </Grid>
  )
}
