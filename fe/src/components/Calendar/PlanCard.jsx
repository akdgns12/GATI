import React from 'react'
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

import Grid from '@mui/material/Unstable_Grid2';

export default function PlanCard(props) {

  const plan = props.plan;

  return (

      <Card value= 'sivla' sx={{ justifyContent: 'center', m: 2, backgroundColor: '#685DD833'}} variant="outlined">
        <CardContent>
          <Typography sx={{ fontSize: 25 }} color="text.secondary" gutterBottom>
            {plan.title}
          </Typography>
          
          <Typography sx={{ mb: 2 }}  variant="body1">
            {plan.content}
          </Typography>

          <Typography variant="body2">
            {plan.start}
          </Typography>

          <Typography variant="body2">
            {plan.end}
          </Typography>
        </CardContent>
      </Card>
  )
}
