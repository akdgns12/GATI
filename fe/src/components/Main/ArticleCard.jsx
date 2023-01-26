import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';


export default function ArticleCard() {

  var comment_count = 4;
  var like_count = 3;

  const mvToDetail = () => {
    console.log("move to detail page");
    alert("move to detail page");
  };

  function showOptions() {
    console.log("show options");
    alert("show options");
  };

  function toggleFav() {
    console.log("add/remove this article to/from favorite");
    alert("toggle icon effects");
  };

  function addToPhotoBook() {
    console.log("add article to Photo Book");
    alert("add article to photo book");
  }

  return (
    <Card sx={{ maxWidth: "100%" }} style={{ marginBottom: "10px" }}>
      <CardHeader
        action={
          <IconButton aria-label="settings" onClick={showOptions}>
            <MoreHorizIcon />
          </IconButton>
        }
        title="user_name"
        titleTypographyProps={{ variant: 'body1' }}
        style={{ textAlign: 'left' }}
      />
      <CardMedia
        component="img"
        height="100%"
        image="https://picsum.photos/400/300"
        alt="No photo"
      />
      
      <CardActions disableSpacing={true}>
        <IconButton aria-label="add to favorites" onClick={toggleFav}>
          <FavoriteIcon />
        </IconButton>
        { like_count }
        <div style={{ marginLeft: "auto" }}>
          <Typography variant='body4'>2023.01.26</Typography>
          <IconButton aria-label="bookmark" onClick={addToPhotoBook}>
            <BookmarkBorderIcon />
          </IconButton>
        </div>
      </CardActions>

      <CardContent>
        <Typography variant="body2" style={{ textAlign: 'left' }}>
          short description
        </Typography>
      </CardContent>

      <CardActions onClick={mvToDetail} style={{ height: "30px" }}>
        <Typography variant='body2' style={{ marginLeft: 10}}>
          Comment {comment_count}
        </Typography>
      </CardActions>

    </Card>
  );
}