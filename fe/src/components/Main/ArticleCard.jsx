import * as React from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

export default function ArticleCard(props) {
  const article = props.article;

  const mvToDetail = () => {
    console.log("move to detail page");
    alert("move to detail page");
  };

  function showOptions() {
    console.log("show options");
    alert("show options");
  }

  function toggleFav() {
    console.log("add/remove this article to/from favorite");
    alert("toggle icon effects");
  }

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
        title={article.userId}
        titleTypographyProps={{ variant: "body1" }}
        style={{ textAlign: "left" }}
      />
      {article.img != null ? (
        <CardMedia component="img" width="100%" image={article.img} alt="No photo" />
      ) : (
        "no img"
      )}

      <CardActions disableSpacing={true}>
        <IconButton aria-label="add to favorites" onClick={toggleFav}>
          <FavoriteIcon />
        </IconButton>
        {article.like}
        <div style={{ marginLeft: "auto" }}>
          <Typography variant="body4">{article.createTime}</Typography>
          <IconButton aria-label="bookmark" onClick={addToPhotoBook}>
            <BookmarkBorderIcon />
          </IconButton>
        </div>
      </CardActions>

      <CardContent>
        <Typography variant="body2" style={{ textAlign: "left" }}>
          {article.content}
        </Typography>
      </CardContent>

      <CardActions onClick={mvToDetail} style={{ height: "30px" }}>
        <Typography variant="body2" style={{ marginLeft: 10 }}>
          Comment &nbsp;
          {article.comment > 0 && article.comment}
        </Typography>
      </CardActions>
    </Card>
  );
}
